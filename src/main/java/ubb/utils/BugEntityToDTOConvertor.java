package ubb.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ubb.controller.DTOS.BugDTO;
import ubb.repository.entity.BugEntity;
@Component
public class BugEntityToDTOConvertor  implements Converter<BugEntity, BugDTO> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public BugDTO convert(BugEntity source) {
        BugDTO bug = new BugDTO();
        bug.setId(source.getId());
        bug.setTitle(source.getTitle());
        bug.setDescription(source.getDescription());
        bug.setStatus(source.getStatus());
        return bug;
    }
}
