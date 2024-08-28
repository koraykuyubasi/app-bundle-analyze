# App Bundle Analyze

## Apk Contents
    AndroidManifest.xml 
    classes*.dex
    resources.asrc
    res/
    assets/
    lib/
    kotlin/
    DebugProbesKt.bin
    META-INF/
    play-services-basement.properties
    play-services-tasks.properties
    asset-delivery-ktx.properties
    asset-delivery.properties
    core-common.properties
    kotlin-tooling-metadata.json (release only)

- To read resources.asrc refer to [ResourceDump](https://github.com/koraykuyubasi/review/blob/main/README.md#resourcedump)


## Bundle Contents
    base/
        dex/            - compilation of classes that was in the apk
        lib/            - abi related
        resources.pb    - strings etc.
        res/            - density related
        assets/
        root/
        META-INF/
        native.pb
        assets.pb
        manifest/
    dynamic*/
        lib/
        assets/
        dex/
        manifest/
        res/
        native.pb
        assets.pb
        resources.pb
    asset_pack_*/
        assets/
        manifest/
        assets.pb
    BUNDLE-METADATA/
    BundleConfig.pb

- To read .pb files refer to [ProtoBuffer](https://github.com/koraykuyubasi/review/blob/main/README.md#protobuffer)


## All Apks Generated From Bundle
```
bundletool build-apks --bundle=app/build/outputs/bundle/debug.aab --output=tmp/all.apks
```
    Contents

        splits/                             - fragments of apk divided according to bundle{} in app/build.gradle
            base.(master).apk
                base-master.apk             - compressed
                base-master_2.apk           - uncompressed
            base-(density).apk
                base-xhdpi.apk
                base-xxhdpi.apk
                ...
            base-(language).apk
                base-tr.apk
                base-fr.apk
                ...
            base-(abi).apk
                base-x86.apk
                base-arm64_v8a.apk
                ...
            dynamic*-(master).apk
                dynamic*-master.apk
                dynamic*-master_2.apk
            dynamic*-(language).apk
                dynamic*-tr.apk
                dynamic*-en.apk
                ...
            dynamic*-(abi).apk
                dynamic*-x86.apk
                dynamic*-arm64_v8a.apk
                ...
            dynamic1-(density).apk //TODO
        instant/
            instant-base.(master).apk
                instant-base-master.apk
            instant-base-(density).apk
                instant-base-xhdpi.apk
                instant-base-xxhdpi.apk
                ...
            instant-base-(language).apk
                instant-base-tr.apk
                instant-base-fr.apk
                ...
            instant-base-(abi).apk
                instant-base-x86.apk
                instant-base-arm64_v8a.apk
                ...
            instant-dynamic2.(master).apk
                instant-dynamic2-master.apk
                ...
            instant-dynamic2.(language).apk
                instant-dynamic2.fr.apk
                instant-dynamic2.en.apk
                ...
            instant-dynamic2.(abi).apk
                instant-dynamic2.x86.apk
                instant-dynamic2.arm64_v8a.apk
                ...
        
        asset-slices/
            asset_pack_*.master.apk
        toc.pb                              - all variants and app descriptions
    
- To read .pb files refer to [ProtoBuffer](https://github.com/koraykuyubasi/review/blob/main/README.md#protobuffer)

## Universal(Single) Apks Contents
```
bundletool build-apks --bundle=app/build/outputs/bundle/debug.aab --output=tmp/universal.apks --mode=universal
```
    universal.apk
    toc.pb
    
- To read .pb files refer to [ProtoBuffer](https://github.com/koraykuyubasi/review/blob/main/README.md#protobuffer)

## ProtoBuffer
    Note: Credits to https://github.com/protocolbuffers/protobuf

    resources.pb //TODO
        protoc --experimental_allow_proto3_optional \
           --proto_path=proto \
           --decode=android.bundle.BuildApksResult \
           commands.proto \
            < resources.pb > resources.raw

    toc.pb
        protoc --experimental_allow_proto3_optional \
           --proto_path=proto \
           --decode=android.bundle.BuildApksResult \
           commands.proto \
            < toc.pb > toc.raw

    native.pb
        protoc --experimental_allow_proto3_optional \
           --proto_path=proto \
           --decode=android.bundle.NativeLibraries \
           files.proto \
            < native.pb > native.raw

    assets.pb
        protoc --experimental_allow_proto3_optional \
           --proto_path=proto \
           --decode=android.bundle.Assets \
           files.proto \
            < assets.pb > assets.raw

    BundleConfig.pb
        protoc --experimental_allow_proto3_optional \
           --proto_path=proto \
           --decode=android.bundle.BundleConfig \
           config.proto \
            < BundleConfig.pb > BundleConfig.raw


## ResourceDump
    resources.asrc
        aapt2 dump resources app/build/outputs/apk/debug/app-debug.apk > tmp/app-debug.apk.resources.asrc.dump
