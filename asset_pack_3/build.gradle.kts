plugins {
    id("com.android.asset-pack")
}

assetPack {
    packName.set("asset_pack_3")
    dynamicDelivery {
        deliveryType.set("on-demand")
    }
}