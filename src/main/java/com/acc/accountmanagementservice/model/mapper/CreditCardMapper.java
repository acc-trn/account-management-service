package com.acc.accountmanagementservice.model.mapper;

import com.acc.accountmanagementservice.entity.CreditCard;
import com.acc.accountmanagementservice.model.CreditCardDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {

    CreditCardDto modelToDto(CreditCard creditCard);

    List<CreditCardDto> modelsToDtos(List<CreditCard> creditCards);

    // Mapping DTO back to Entity (Optional, if needed)
    CreditCard dtoToModel(CreditCardDto creditCardDto);
}
