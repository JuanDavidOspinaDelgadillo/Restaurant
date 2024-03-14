package com.group5.Restaurant.errors.validations;

import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.ClientDTO;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ClientDTOValidation {
    public void validateClientDTO(ClientDTO clientDTO) throws BadRequestException {
        List<String> validDocumentTypes = Arrays.asList("CC", "TI", "CE");
        if      ((clientDTO.getClientDocument() == null || clientDTO.getClientDocument() <= 0) ||
                (clientDTO.getClientDocumentType() == null || !validDocumentTypes.contains(clientDTO.getClientDocumentType())) ||
                (clientDTO.getClientEmail() == null || !clientDTO.getClientEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) ||
                (clientDTO.getClientPhoneNumber() == null || clientDTO.getClientPhoneNumber().trim().isEmpty()) ||
                (clientDTO.getClientAddress() == null || clientDTO.getClientAddress().trim().isEmpty()))
            throw new BadRequestException(ResponseCodes.NULL_OR_INVALID_DATA);
    }

    public void validateClientDocument(Long clientDocument) throws BadRequestException {
        if(clientDocument == null || clientDocument <= 0)
            throw new BadRequestException(ResponseCodes.NULL_OR_INVALID_DATA);
    }

    public void validateTypeOfDataAndDirection(String typeOfData, String direction) throws BadRequestException{
        Set<String> possibleTypeOfData = new HashSet<>();
        possibleTypeOfData.add("DOCUMENT");
        possibleTypeOfData.add("NAME");
        possibleTypeOfData.add("ADDRESS");
        Set<String> possibleDirections = new HashSet<>();
        possibleDirections.add("ASC");
        possibleDirections.add("DESC");
        if(typeOfData.isEmpty() || direction.isEmpty() || !possibleTypeOfData.contains(typeOfData) || !possibleDirections.contains(direction))
            throw new BadRequestException(ResponseCodes.NULL_OR_INVALID_DATA);
    }
}