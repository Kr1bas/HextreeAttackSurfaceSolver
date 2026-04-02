package com.sntrain.kribas.hextree1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Iterator;
import java.util.Set;

public class DumpIntent {
    public static String dumpIntent(Context context, Intent intent) {
        return dumpIntent(context, intent, 0);
    }
    private static String dumpIntent(Context context, Intent intent, int i) {
        if (intent == null) {
            return "Intent is null";
        }
        StringBuilder sb = new StringBuilder();
        String strReplace = new String(new char[i]).replace("\u0000", "    ");
        sb.append(strReplace).append("[Action]    ").append(intent.getAction()).append("\n");
        Set<String> categories = intent.getCategories();
        if (categories != null) {
            Iterator<String> it = categories.iterator();
            while (it.hasNext()) {
                sb.append(strReplace).append("[Category]  ").append(it.next()).append("\n");
            }
        }
        sb.append(strReplace).append("[Data]      ").append(intent.getDataString()).append("\n");
        sb.append(strReplace).append("[Component] ").append(intent.getComponent()).append("\n");
        sb.append(strReplace).append("[Flags]     ").append(getFlagsString(intent.getFlags())).append("\n");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            for (String str : extras.keySet()) {
                Object obj = extras.get(str);
                if (obj instanceof Intent) {
                    sb.append(strReplace).append("[Extra:'").append(str).append("'] -> Intent\n");
                    sb.append(dumpIntent(context, (Intent) obj, i + 1));
                } else if (obj instanceof Bundle) {
                    sb.append(strReplace).append("[Extra:'").append(str).append("'] -> Bundle\n");
                    sb.append(dumpBundle((Bundle) obj, i + 1));
                } else {
                    sb.append(strReplace).append("[Extra:'").append(str).append("']: ").append(obj).append("\n");
                }
            }
        }
        return sb.toString();
    }

    private static String getFlagsString(int i) {
        StringBuilder sb = new StringBuilder();
        if ((i & 1) != 0) {
            sb.append("GRANT_READ_URI_PERMISSION | ");
        }
        if ((i & 2) != 0) {
            sb.append("GRANT_WRITE_URI_PERMISSION | ");
        }
        if ((i & 64) != 0) {
            sb.append("GRANT_PERSISTABLE_URI_PERMISSION | ");
        }
        if ((i & 128) != 0) {
            sb.append("GRANT_PREFIX_URI_PERMISSION | ");
        }
        int i2 = 268435456 & i;
        if (i2 != 0) {
            sb.append("ACTIVITY_NEW_TASK | ");
        }
        int i3 = 536870912 & i;
        if (i3 != 0) {
            sb.append("ACTIVITY_SINGLE_TOP | ");
        }
        int i4 = 1073741824 & i;
        if (i4 != 0) {
            sb.append("ACTIVITY_NO_HISTORY | ");
        }
        if ((67108864 & i) != 0) {
            sb.append("ACTIVITY_CLEAR_TOP | ");
        }
        if ((33554432 & i) != 0) {
            sb.append("ACTIVITY_FORWARD_RESULT | ");
        }
        if ((16777216 & i) != 0) {
            sb.append("ACTIVITY_PREVIOUS_IS_TOP | ");
        }
        if ((8388608 & i) != 0) {
            sb.append("ACTIVITY_EXCLUDE_FROM_RECENTS | ");
        }
        if ((4194304 & i) != 0) {
            sb.append("ACTIVITY_BROUGHT_TO_FRONT | ");
        }
        int i5 = 2097152 & i;
        if (i5 != 0) {
            sb.append("ACTIVITY_RESET_TASK_IF_NEEDED | ");
        }
        if ((1048576 & i) != 0) {
            sb.append("ACTIVITY_LAUNCHED_FROM_HISTORY | ");
        }
        int i6 = 524288 & i;
        if (i6 != 0) {
            sb.append("ACTIVITY_CLEAR_WHEN_TASK_RESET | ");
        }
        if (i6 != 0) {
            sb.append("ACTIVITY_NEW_DOCUMENT | ");
        }
        if ((262144 & i) != 0) {
            sb.append("ACTIVITY_NO_USER_ACTION | ");
        }
        if ((131072 & i) != 0) {
            sb.append("ACTIVITY_REORDER_TO_FRONT | ");
        }
        if ((65536 & i) != 0) {
            sb.append("ACTIVITY_NO_ANIMATION | ");
        }
        if ((32768 & i) != 0) {
            sb.append("ACTIVITY_CLEAR_TASK | ");
        }
        if ((i & 16384) != 0) {
            sb.append("ACTIVITY_TASK_ON_HOME | ");
        }
        if ((i & 8192) != 0) {
            sb.append("ACTIVITY_RETAIN_IN_RECENTS | ");
        }
        if ((i & 4096) != 0) {
            sb.append("ACTIVITY_LAUNCH_ADJACENT | ");
        }
        if ((i & 512) != 0) {
            sb.append("ACTIVITY_REQUIRE_DEFAULT | ");
        }
        if ((i & 1024) != 0) {
            sb.append("ACTIVITY_REQUIRE_NON_BROWSER | ");
        }
        if ((i & 2048) != 0) {
            sb.append("ACTIVITY_MATCH_EXTERNAL | ");
        }
        int i7 = i & 134217728;
        if (i7 != 0) {
            sb.append("ACTIVITY_MULTIPLE_TASK | ");
        }
        if (i4 != 0) {
            sb.append("RECEIVER_REGISTERED_ONLY | ");
        }
        if (i3 != 0) {
            sb.append("RECEIVER_REPLACE_PENDING | ");
        }
        if (i2 != 0) {
            sb.append("RECEIVER_FOREGROUND | ");
        }
        if (i7 != 0) {
            sb.append("RECEIVER_NO_ABORT | ");
        }
        if (i5 != 0) {
            sb.append("RECEIVER_VISIBLE_TO_INSTANT_APPS | ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 3);
        }
        return sb.toString();
    }

    public static String dumpBundle(Bundle bundle) {
        return dumpBundle(bundle, 0);
    }

    private static String dumpBundle(Bundle bundle, int i) {
        if (bundle == null) {
            return "Bundle is null";
        }
        StringBuilder sb = new StringBuilder();
        String strReplace = new String(new char[i]).replace("\u0000", "    ");
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                sb.append(String.format("%s['%s']: Bundle[\n%s%s]\n", strReplace, str, dumpBundle((Bundle) obj, 1 + i), strReplace));
            } else {
                Object[] objArr = new Object[3];
                objArr[0] = strReplace;
                objArr[1] = str;
                objArr[2] = obj != null ? obj.toString() : "null";
                sb.append(String.format("%s['%s']: %s\n", objArr));
            }
        }
        return sb.toString();
    }
}

