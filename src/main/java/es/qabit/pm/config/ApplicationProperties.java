package es.qabit.pm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Points Management.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link tech.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private Integer defaultAmountPoints;

    public Integer getDefaultAmountPoints() {
        return defaultAmountPoints;
    }

    public void setDefaultAmountPoints(Integer defaultAmountPoints) {
        this.defaultAmountPoints = defaultAmountPoints;
    }
}
