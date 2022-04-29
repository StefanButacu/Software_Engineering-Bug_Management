package ubb.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ubb.controller.DTOS.RoleDTO;
import ubb.repository.entity.RoleEntity;

@Component
public class RoleEntityToDTOConvertor implements Converter<RoleEntity, RoleDTO> {
    @Override
    public RoleDTO convert(RoleEntity source) {
        RoleDTO roleDTO= new RoleDTO();
        roleDTO.setRole(source.getRole());
        roleDTO.setId(source.getId());
        return roleDTO;
    }
}
