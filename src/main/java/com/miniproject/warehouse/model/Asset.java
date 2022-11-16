package com.miniproject.warehouse.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "asset")
public class Asset {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "asset_barcode", updatable = false, nullable = false )
    private UUID assetBarcode;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "asset_description")
    private String assetDescription;

    @Column(name = "asset_information")
    private String assetInformation;

}
