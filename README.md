# HextreeAttackSurfaceSolver
Main app used to solve the AttackSurface flags on hextree

# Currently Available Flags
| **Flag** | **Category**      | **Solved** |
|----------|-------------------|------------|
| **1**    | Activity          | yes        |
| **2**    | Activity          | yes        |
| **3**    | Activity          | yes        |
| **4**    | Activity          | yes        |
| **5**    | Activity          | yes        |
| **6**    | Activity          | yes        |
| **7**    | Activity          | yes        |
| **8**    | ActivityResult    | yes        |
| **9**    | ActivityResult    | yes        |
| **10**   | ImplicitIntent    | yes        |
| **11**   | ImplicitIntent    | yes        |
| **12**   | ImplicitIntent    | yes        |
| **13**   | Deeplink          | yes        |
| **14**   | Deeplink          | yes        |
| **15**   | Deeplink          | yes        |
| **16**   | BroadcastReceiver | yes        |
| **17**   | BroadcastReceiver | yes        |
| **18**   | BroadcastReceiver | yes        |
| **19**   | BroadcastReceiver | yes        |
| **20**   | BroadcastReceiver | yes (?)    |
| **21**   | BroadcastReceiver | yes        |
| **22**   | PendingIntent     | yes        |
| **23**   | PendingIntent     | yes        |
| **24**   | Service           | yes        |
| **25**   | Service           | yes*       |
| **26**   | Service           | yes        |
| **27**   | Service           | yes        |
| **28**   | Service           | yes        |
| **29**   | Service           | yes        |
| **30**   | ContentProvider   | yes        |
| **31**   | ContentProvider   | yes        |
| **32**   | ContentProvider   | yes        |
| **33.1** | ContentProvider   | yes        |
| **33.2** | ContentProvider   | no         |
| **34**   | FileProvider      | yes        |
| **35**   | FileProvider      | yes        |
| **36**   | FileProvider      | yes        |
| **37**   | FileProvider      | yes        |
| **38**   | WebView           | yes        |
| **39**   | WebView           | yes        |
| **40**   | WebView           | yes        |
| **41**   | WebView           | no         |


#### Notes
- **Flag 25**, like all service challenges, is a bit buggy on my emulator, the concept is in the code however it works better when starting the service via adb while the AttackSurface app is open: 
  - `adb shell am startservice -n io.hextree.attacksurface/io.hextree.attacksurface.services.Flag25Service -a io.hextree.services.UNLOCK1`
  - `adb shell am startservice -n io.hextree.attacksurface/io.hextree.attacksurface.services.Flag25Service -a io.hextree.services.UNLOCK2`
  - `adb shell am startservice -n io.hextree.attacksurface/io.hextree.attacksurface.services.Flag25Service -a io.hextree.services.UNLOCK3`