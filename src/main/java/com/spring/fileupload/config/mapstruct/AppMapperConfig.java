package com.spring.fileupload.config.mapstruct;

import org.mapstruct.Builder;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * The default config for MapStruct mappers in this app.
 */
@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true)
)
public interface AppMapperConfig {

}