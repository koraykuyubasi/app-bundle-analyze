#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_myapplication_MainActivity_stringFromTest(
        JNIEnv* env,
        jobject /* this */) {
    std::string string = "libDynamic2";
    return env->NewStringUTF(string.c_str());
}