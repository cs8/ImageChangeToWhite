#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_android_chen_myapplication_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jintArray JNICALL
Java_com_android_chen_myapplication_NdkImageUtils_getNdkImage(JNIEnv *env, jclass type,
                                                              jintArray buffer, jint width,
                                                              jint height) {
//    jint *buffer = env->GetIntArrayElements(buffer, NULL);

    // TODO
    //用c指针
    jint * source =env->GetIntArrayElements(buffer, NULL);

    float brigtenss  = 0.2f;
    float contrinst = 0.2f;
    int newSize =width*height;
    int a,r,g,b;
    int bab =(int)(255*brigtenss);
    float ca =1.0f+contrinst;

    for(int i=0;i<width;i++){
        for (int j=0;j<height;j++){
//            int color =source.getPixel(i,j);
//            a= Color.alpha(color);
//            r= Color.red(color);
//            g= Color.green(color);
//            b=Color.blue(color);

            int color = source[width*j+i];
            a =color >>24;
            r =(color>>16)&0xff;
            g =(color>>8)&0xff;
            b= color&0xff;

            //美白 亮度
            int r_b=r+bab;
            int g_b=g+bab;
            int b_b=b+bab;
            //边界检测 范围控制在0至255之间
            r =r_b>255?255:(r_b<0?0:r_b);
            g =g_b>255?255:(g_b<0?0:g_b);
            b =b_b>255?255:(b_b<0?0:b_b);

            //扩大对比度
            r_b=r-128;
            g_b=g-128;
            b_b=b-128;

            r_b=(int)(r_b*ca);
            g_b=(int)(g_b*ca);
            b_b=(int)(b_b*ca);

            r_b=r_b+128;
            g_b=g_b+128;
            b_b=b_b+128;

            //色值边界检测 范围控制在0至255之间
            r =r_b>255?255:(r_b<0?0:r_b);
            g =g_b>255?255:(g_b<0?0:g_b);
            b =b_b>255?255:(b_b<0?0:b_b);
//            result.setPixel(i,j,Color.argb(a,r,g,b));

            source[width *j+i]=(a<<24)|(r<<16)|(g<<8)|b;

        }
    }
    printf("------>width=%d",width);
    printf("------>height=%d",height);
    //保存到jintArray资源释放
    jintArray result =env->NewIntArray(newSize);
    //保存进result
    env->SetIntArrayRegion(result,0,newSize,source);
    //释放资源
    env->ReleaseIntArrayElements(buffer,source,0);
    return result;
}