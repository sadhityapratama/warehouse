package com.miniproject.warehouse.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getAssetInformation() {
        return assetInformation;
    }

    public void setAssetInformation(String assetInformation) {
        this.assetInformation = assetInformation;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }


}
