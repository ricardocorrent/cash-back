package com.ricardocorrent.jwt.order

import com.ricardocorrent.jwt.cashback.rule.TenPercent
import com.ricardocorrent.jwt.exception.UserNotFoundException
import com.ricardocorrent.jwt.user.UserDetailsImpl
import com.ricardocorrent.jwt.user.UserRepository
import com.ricardocorrent.jwt.utils.cpf_approved
import com.ricardocorrent.jwt.utils.formatDecimal
import com.ricardocorrent.jwt.utils.isEqTo
import com.ricardocorrent.jwt.utils.justNumbers
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
    val repository: OrderRepository,
    val userRepository: UserRepository,
) {

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

        entity.cashBackPercentage = percentage.formatDecimal()
        entity.cashBackValue = value
    }

}
