#include <jni.h>
#include <string.h>

extern "C" {

			JNIEXPORT jstring JNICALL

			Java_com_equiplexdevelopers_budgetdroid_BudgetDroidcpp_Reminder
			(JNIEnv *env , jobject obj)
			{
				return env -> NewStringUTF("C++ JNI action not yet implemented");
			}
}

