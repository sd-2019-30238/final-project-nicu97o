package com.shoppinglist.model.mapper;

public interface Mapper<DB, DTO> {
    DB convertToEntity(DTO dto);

    DTO convertToDTO(DB db);
}
