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

<<<<<<< HEAD
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


=======
>>>>>>> e2a5c33543356144dbe10009f76bb2fbb5e61ce5
}
