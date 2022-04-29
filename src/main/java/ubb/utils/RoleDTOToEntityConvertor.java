package ubb.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ubb.controller.DTOS.RoleDTO;
import ubb.repository.entity.RoleEntity;

@Component
public class RoleDTOToEntityConvertor  implements Converter<RoleDTO, RoleEntity> {
    @Override
    public RoleEntity convert(RoleDTO source) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(source.getId());
        roleEntity.setRole(source.getRole());
        return roleEntity;
    }
}
