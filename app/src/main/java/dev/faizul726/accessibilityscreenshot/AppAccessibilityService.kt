package dev.faizul726.accessibilityscreenshot

import android.accessibilityservice.AccessibilityButtonController
import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

/**
 * Reference:
 * https://android.googlesource.com/platform/frameworks/base/+/main/packages/SystemUI/accessibility/accessibilitymenu/src/com/android/systemui/accessibility/accessibilitymenu/AccessibilityMenuService.java
 * https://developer.android.com/reference/android/accessibilityservice/AccessibilityService.html#GLOBAL_ACTION_TAKE_SCREENSHOT
 */

class AppAccessibilityService : AccessibilityService() {
    override fun onServiceConnected() {
        accessibilityButtonController.registerAccessibilityButtonCallback(
            object : AccessibilityButtonController.AccessibilityButtonCallback() {
                override fun onClicked(controller: AccessibilityButtonController?) {
                    performGlobalAction(GLOBAL_ACTION_TAKE_SCREENSHOT)
                }
            }
        )
    }

    override fun onInterrupt() {}
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
}