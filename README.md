# App Bundle Analyze

## Apk Contents
    AndroidManifest.xml 
    classes*.dex
    resources.asrc
    res/
    assets/
    lib/
    kotlin/
    META-INF/
    ...

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
        ...
    dynamic1/
        dex/
        manifest/
        res/
        resources.pb
    BUNDLE-METADATA/
    BundleConfig.pb

- To read .pb files refer to [ProtoBuffer](https://github.com/koraykuyubasi/review/blob/main/README.md#protobuffer)


## All Apks Generated From Bundle
```
bundletool build-apks --bundle=app/build/outputs/bundle/debug --output=tmp/all.apks
```
    Contents

        splits/                             - fragments of apk divided according to bundle{} in app/build.gradle
            base.(master).apk
                base-master.apk
                base-master_2.apk
            base-(density).apk
                base-xhdpi.apk
                ...
            base-(language).apk
                base-tr.apk
                ...
            dynamic1-(master).apk
                dynamic1-master.apk
                dynamic1-master_2.apk
        toc.pb                              - all variants and app descriptions
    
- To read .pb files refer to [ProtoBuffer](https://github.com/koraykuyubasi/review/blob/main/README.md#protobuffer)

## Universal(Single) Apks Contents
```
bundletool build-apks --bundle=app/build/outputs/bundle/debug --output=tmp/universal.apks -- mode=universal
```
    universal.apk
    toc.pb
    
- To read .pb files refer to [ProtoBuffer](https://github.com/koraykuyubasi/review/blob/main/README.md#protobuffer)

## ProtoBuffer
    Note: Credits to https://github.com/protocolbuffers/protobuf

    resources.pb
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
           --decode=android.bundle.BuildSdkApksResult \
           commands.proto \
            < native.pb > native.raw

    assets.pb
        protoc --experimental_allow_proto3_optional \
           --proto_path=proto \
           --decode=android.bundle.BuildSdkApksResult \
           commands.proto \
            < assets.pb > assets.raw

    BundleConfig.pb
        protoc --experimental_allow_proto3_optional \
           --proto_path=proto \
           --decode=android.bundle.BuildSdkApksResult \
           commands.proto \
            < BundleConfig.pb > BundleConfig.raw


## ResourceDump
    resources.asrc
        aapt2 dump resources app/build/outputs/apk/debug/app-debug.apk > tmp/app-debug.apk.resources.asrc.dump
