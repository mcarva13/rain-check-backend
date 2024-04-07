package rain.check.backend.database.domain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * City Java Persistence object.
 */
@Table(name = "city", schema = "rain_check")
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class CityJPA extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "longitude")
    @NotNull
    private BigDecimal longitude;

    @Column(name = "latitude")
    @NotNull
    private BigDecimal latitude;

    @Column(name = "country")
    @NotEmpty
    private String country;

    @Column(name = "timezone")
    @NotEmpty
    private String timezone;

    public static CityJPA findByName(String name){
        String searchInput = "%" + name + "%";

        return find("lower(cityName) like concat('%', lower(?1), '%')", searchInput).firstResult();
    }
}
