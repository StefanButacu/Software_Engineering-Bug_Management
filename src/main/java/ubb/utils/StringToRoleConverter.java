package ubb.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ubb.controller.DTOS.RoleDTO;
import ubb.repository.RoleRepository;
import ubb.repository.entity.RoleEntity;

import java.util.Optional;

@Component
public class StringToRoleConverter implements Converter<String, RoleDTO> {

    private final RoleRepository roleEntityRepository;
    private final RoleEntityToDTOConvertor roleEntityToDTOConvertor;
    @Autowired
    public StringToRoleConverter(RoleRepository roleRepository, RoleEntityToDTOConvertor roleEntityToDTOConvertor) {
        this.roleEntityRepository = roleRepository;
        this.roleEntityToDTOConvertor = roleEntityToDTOConvertor;
    }

    /**
     * Gets the RoleModel by it's roleName
     * @param roleName - String
     * @return RoleModel object from repository which has same role as roleName
     */
    @Override
    public RoleDTO convert(String roleName) {
        Optional<RoleEntity> roleEntityOptional = roleEntityRepository.findRoleEntityByName(roleName);
        return roleEntityToDTOConvertor.convert(roleEntityOptional
                .orElseThrow(
                        ()->new ApplicationException("Not exist such role with this name="+roleName)));
    }
}
