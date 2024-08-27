#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_libB_LibB_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string string = "LibB";
    return env->NewStringUTF(string.c_str());
}