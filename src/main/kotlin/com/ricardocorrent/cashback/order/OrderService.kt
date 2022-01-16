package com.ricardocorrent.cashback.order

import com.ricardocorrent.cashback.cashback.CashBack
import com.ricardocorrent.cashback.cashback.rule.TenPercent
import com.ricardocorrent.cashback.exception.UserNotFoundException
import com.ricardocorrent.cashback.user.UserDetailsImpl
import com.ricardocorrent.cashback.user.UserRepository
import com.ricardocorrent.cashback.utils.cpf_approved
import com.ricardocorrent.cashback.utils.formatDecimal
import com.ricardocorrent.cashback.utils.isEqTo
import com.ricardocorrent.cashback.utils.justNumbers
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    private val repository: OrderRepository,
    private val userRepository: UserRepository,
) {

    private companion object {
        private val LOGGER = LoggerFactory.getLogger(OrderService::class.java)
    }

    fun save(entity: Order): Order {
        entity.id = UUID.randomUUID()
        entity.user = findUserByCpfOrThrowUserNotFoundException(entity.getUserCpf())
        handleStatus(entity)
        handleCashBack(entity)

        return repository.save(entity)
    }

    fun findAll(user: UserDetails): List<Order> {
        val userId = handleUser(user)

        return repository.findAllByUserIdOrderByDateDesc(userId)
            ?.toList()
            ?: emptyList()
    }

    private fun handleUser(user: UserDetails): UUID {
        return (user as UserDetailsImpl).getUserId()
            ?: userRepository.findByEmail(user.username)!!.id!!
    }

    private fun findUserByCpfOrThrowUserNotFoundException(cpf: String) =
        userRepository.findByCpf(cpf)
            ?: throw UserNotFoundException(cpf)

    private fun handleStatus(entity: Order) {
        val cpfToBeVerified = entity.getUserCpf().justNumbers()
        val approvedCpf = cpf_approved.justNumbers()

        entity.status =
            if (cpfToBeVerified isEqTo approvedCpf) OrderStatusEnum.APPROVED
            else OrderStatusEnum.VALIDATION
    }

    private fun handleCashBack(entity: Order) {
        val (percentage, value) = TenPercent.calculateCashBackValue(entity.value)

        entity.cashBack = CashBack(percentage.formatDecimal(), value)
    }

}
