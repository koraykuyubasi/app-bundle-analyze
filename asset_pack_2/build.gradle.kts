plugins {
    id("com.android.asset-pack")
}

assetPack {
    packName.set("asset_pack_2")
    dynamicDelivery {
        deliveryType.set("fast-follow")
    }
}