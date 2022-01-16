package com.ricardocorrent.cashback.user.converter

import com.ricardocorrent.cashback.user.User
import com.ricardocorrent.cashback.user.dto.UserRequestDto
import com.ricardocorrent.cashback.user.dto.UserResponseDto
import org.mapstruct.*

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface UserConverter {

    @Named("userToUserResponseDto")
    fun toDto(source: User): UserResponseDto

    @Mappings(
        value = [
            Mapping(target = "id", ignore = true),
        ]
    )
    @Named("userRequestDtoToUser")
    fun toEntity(source: UserRequestDto): User

}