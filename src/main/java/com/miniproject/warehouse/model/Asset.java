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
    @Column(name = "barcode", updatable = false, nullable = false )
    private String barcode;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "asset_description")
    private String assetDescription;

    @Column(name = "asset_information")
    private String assetInformation;

}
