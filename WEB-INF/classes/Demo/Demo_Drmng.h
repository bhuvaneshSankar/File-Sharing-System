/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class Demo_Drmng */

#ifndef _Included_Demo_Drmng
#define _Included_Demo_Drmng
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     Demo_Drmng
 * Method:    createDirectory
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_Demo_Drmng_createDirectory
  (JNIEnv *, jobject, jstring);

/*
 * Class:     Demo_Drmng
 * Method:    deleteDirectory
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_Demo_Drmng_deleteDirectory
  (JNIEnv *, jobject, jstring);

/*
 * Class:     Demo_Drmng
 * Method:    listDirectoryContents
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_Demo_Drmng_listDirectoryContents
  (JNIEnv *, jobject, jstring);

/*
 * Class:     Demo_Drmng
 * Method:    createFile
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_Demo_Drmng_createFile
  (JNIEnv *, jobject, jstring);

/*
 * Class:     Demo_Drmng
 * Method:    deleteFile
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_Demo_Drmng_deleteFile
  (JNIEnv *, jobject, jstring);

/*
 * Class:     Demo_Drmng
 * Method:    appendDataToFile
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_Demo_Drmng_appendDataToFile
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     Demo_Drmng
 * Method:    getDirectoryStructure
 * Signature: ()Ljava/util/ArrayList;
 */
JNIEXPORT jobject JNICALL Java_Demo_Drmng_getDirectoryStructure
  (JNIEnv *, jobject);

/*
 * Class:     Demo_Drmng
 * Method:    getFile
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_Demo_Drmng_getFile
  (JNIEnv *, jobject, jstring);

/*
 * Class:     Demo_Drmng
 * Method:    saveFile
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_Demo_Drmng_saveFile
  (JNIEnv *, jobject, jstring, jstring);

#ifdef __cplusplus
}
#endif
#endif
