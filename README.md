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
        resources.pb    - strings etc. To read refer to asd
        res/            - density related
        assets/
        root/
        META-INF/
        native.pb       - To read refer to asd
        assets.pb       - To read refer to asd
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
    splits/         - fragments of apk divided according to bundle{} in app/build.gradle
        
    toc.pb          - all variants and app descriptions
    
- To read .pb files refer to [ProtoBuffer](https://github.com/koraykuyubasi/review/blob/main/README.md#protobuffer)

## Universal(Single) Apks Contents
    universal.apk
    toc.pb
    
- To read .pb files refer to [ProtoBuffer](https://github.com/koraykuyubasi/review/blob/main/README.md#protobuffer)

## bundletool(Generate Apk from bundle)
    Tool to generate apk content from bundle, useful options are shown below:

    --bundle=path (required)
    --output=path (required)
    --mode=universal (optional)
    --device-spec=spec_json (optional)
    --connected-device (optional)

    Usage

        Build Apks:
            bundletool build-apks --connected-device
            bundletool build-apks --device-spec=/MyApp/pixel2.json

        Device Spec:
            bundletool get-device-spec --output=/tmp/device-spec.json

        Extract(NOT TESTED)
            bundletool extract-apks
            --apks=/MyApp/my_existing_APK_set.apks
            --output-dir=/MyApp/my_pixel2_APK_set.apks
            --device-spec=/MyApp/bundletool/pixel2.json

## ProtoBuffer
    Note: Credits to https://github.com/protocolbuffers/protobuf

    resources.pb
        protoc --experimental_allow_proto3_optional \
           --proto_path=proto \
           --decode=android.bundle.BuildApksResult \
           commands.proto \
            < /home/koray/AndroidStudioProjects/MyApplication/toc.pb > toc.raw

    toc.pb
        protoc --experimental_allow_proto3_optional \
           --proto_path=proto \
           --decode=android.bundle.BuildApksResult \
           commands.proto \
            < /home/koray/AndroidStudioProjects/MyApplication/toc.pb > toc.raw

    native.pb
        protoc --experimental_allow_proto3_optional \
           --proto_path=proto \
           --decode=android.bundle.BuildApksResult \
           commands.proto \
            < /home/koray/AndroidStudioProjects/MyApplication/toc.pb > toc.raw

    assets.pb
        protoc --experimental_allow_proto3_optional \
           --proto_path=proto \
           --decode=android.bundle.BuildApksResult \
           commands.proto \
            < /home/koray/AndroidStudioProjects/MyApplication/toc.pb > toc.raw


## ResourceDump
    aapt2 dump resources example.apk > example.apk.rscdump

