package com.bulbul.bestpractice.flightmanagement.entity;

import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "fare_info")
public class FareInfo extends AbstractDomainBasedEntity {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport")
    private Airport departureAirport;
    @Column(name = "departure_airport", insertable = false, updatable = false)
    private Long departureAirportId;
    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    private String cost;
    @Column(name = "unique_code", unique = true)
    private String uniqueCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FareInfo fareInfo)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        return getId() != null ? getId().equals(fareInfo.getId()) : fareInfo.getId() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }
}
