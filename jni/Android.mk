LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := BudgetDroid
LOCAL_SRC_FILES := BudgetDroid.cpp

include $(BUILD_SHARED_LIBRARY)
