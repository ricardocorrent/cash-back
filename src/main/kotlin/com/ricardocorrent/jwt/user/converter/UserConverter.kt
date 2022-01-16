package com.ricardocorrent.jwt.user.converter

import com.ricardocorrent.jwt.user.User
import com.ricardocorrent.jwt.user.dto.UserRequestDto
import com.ricardocorrent.jwt.user.dto.UserResponseDto
import org.mapstruct.*

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface UserConverter {

    @Named("userToUserResponseDto")
    fun toDto(source: User): UserResponseDto

    @Named("userRequestDtoToUser")
    fun toEntity(source: UserRequestDto): User

}