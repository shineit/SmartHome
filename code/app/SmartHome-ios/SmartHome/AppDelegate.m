//
//  AppDelegate.m
//  SmartHome
//
//  Created by Seven on 14-10-15.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "AppDelegate.h"
#import "FELoginVC.h"
#import "FEHomeVC.h"
#import "FECommonNavgationController.h"
#import "FECommonTabBarController.h"
#import "FENewsVC.h"
#import "FECloudSafeVC.h"
#import "FECloudCameraVC.h"
#import "FECloudControlVC.h"
#import "FESettingVC.h"
#import "CDUser.h"
#import "APService.h"
#import "FEHomePageVC.h"
#import "YSPlayerController.h"
#import "YSHTTPClient.h"
#import "YSMobilePages.h"
#import "FEEmptyVC.h"
#import "FECameraShouldVerfyVC.h"
#import "FECommonDefine.h"

@implementation AppDelegate

@synthesize managedObjectContext = _managedObjectContext;
@synthesize managedObjectModel = _managedObjectModel;
@synthesize persistentStoreCoordinator = _persistentStoreCoordinator;
@synthesize coreDataHandler = _coreDataHandler;

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    self.window = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    // Override point for customization after application launch.
    self.window.backgroundColor = [UIColor whiteColor];
    [[UIApplication sharedApplication] setStatusBarHidden:NO withAnimation:UIStatusBarAnimationSlide];
    
    [self loadview];
    [self.window makeKeyAndVisible];
    
    [self registerPushNotificationOptions:launchOptions];
    
    NSMutableDictionary *dictServers = [NSMutableDictionary dictionary];
    
    // SDK 正式地址, 需要开发者申请好appkey方可访问
    [dictServers setObject:@"https://auth.ys7.com" forKey:kAuthServer];
    [dictServers setObject:@"https://open.ys7.com" forKey:kApiServer];
    
    [YSPlayerController loadSDKWithPlatfromServers:dictServers];
    
    // 请使用开发者开发平台申请的appkey
    [[YSHTTPClient sharedInstance] setClientAppKey:YSAppKey secret:YSAppSecret];
    
    return YES;
}


-(void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken{
    
    [APService registerDeviceToken:deviceToken];
    
    NSString *token = [[deviceToken description] stringByTrimmingCharactersInSet:[NSCharacterSet characterSetWithCharactersInString:@"<>"]];
    NSString *devToken = [token stringByReplacingOccurrencesOfString:@" " withString:@""];
    kUserDefaultsSetObjectForKey(devToken, kDeviceToken);
    kUserDefaultsSync;
}

-(void)application:(UIApplication *)application didRegisterUserNotificationSettings:(UIUserNotificationSettings *)notificationSettings{
    
}

-(void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo{
    NSLog(@"remote notification  %@",userInfo);
}

- (void)applicationWillResignActive:(UIApplication *)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    // Saves changes in the application's managed object context before the application terminates.
    [self saveContext];
}

//加载rootviewController
- (void)loadview{
    CDUser *user = [FECoreData fetchUser];
    if (user) {
        [self loadFirstPage];
    }else{
        [self loadSigin];
    }
}

-(void)loadSigin{
    self.tabbarController = nil;
    self.firstPageController = nil;
    FELoginVC *login = [[FELoginVC alloc] init];
    self.window.rootViewController = login;
}

//加载主页
-(void)loadFirstPage{
    if (!self.firstPageController) {
        FEHomePageVC *homeVC = [[FEHomePageVC alloc] init];
        FECommonNavgationController *homen = [[FECommonNavgationController alloc] initWithRootViewController:homeVC];
        self.firstPageController = homen;
    }
    [AppDelegate sharedDelegate].window.rootViewController = self.firstPageController;
}

-(void)loadMainSelectAtIndex:(NSInteger)index{
    
    if (!self.tabbarController) {
        //news page
//        FENewsVC *news = [FENewsVC new];
//        FECommonNavgationController *nvnews = [[FECommonNavgationController alloc] initWithRootViewController:news];
        
        //empty vc
        FEEmptyVC *evc = [FEEmptyVC new];
        
        //云安
        FECloudSafeVC *csafe = [FECloudSafeVC new];
        FECommonNavgationController *nvsafe = [[FECommonNavgationController alloc] initWithRootViewController:csafe];
        
        //云控
        FECloudControlVC *control = [FECloudControlVC new];
        FECommonNavgationController *controlnc = [[FECommonNavgationController alloc] initWithRootViewController:control];
        
        //云视
//        FECloudCameraVC *camera = [FECloudCameraVC new];
        FECameraShouldVerfyVC *cvc = [[FECameraShouldVerfyVC alloc] initWithNibName:@"FECameraShouldVerfyVC" bundle:nil];
        FECommonNavgationController *camnc = [[FECommonNavgationController alloc] initWithRootViewController:cvc];
        
        
        
        //setting
//        FESettingVC *settingvc = [FESettingVC new];
//        FECommonNavgationController *nvsetting = [[FECommonNavgationController alloc] initWithRootViewController:settingvc];
        
        FECommonTabBarController *tabbar = [FECommonTabBarController new];
        [tabbar setViewControllers:@[evc,nvsafe,controlnc,camnc]];
        tabbar.delegate = self;
        self.tabbarController = tabbar;
    }
    [self.tabbarController setSelectedIndex:index];
    [AppDelegate sharedDelegate].window.rootViewController = self.tabbarController;
    
}

- (void)tabBarController:(UITabBarController *)tabBarController didSelectViewController:(UIViewController *)viewController{
    if (tabBarController.selectedIndex == 0) {
        [self loadFirstPage];
    }
}

- (void)saveContext
{
    NSError *error = nil;
    NSManagedObjectContext *managedObjectContext = self.managedObjectContext;
    if (managedObjectContext != nil) {
        if ([managedObjectContext hasChanges] && ![managedObjectContext save:&error]) {
             // Replace this implementation with code to handle the error appropriately.
             // abort() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development. 
            NSLog(@"Unresolved error %@, %@", error, [error userInfo]);
            abort();
        } 
    }
}

#pragma mark - Core Data stack

// Returns the managed object context for the application.
// If the context doesn't already exist, it is created and bound to the persistent store coordinator for the application.
- (NSManagedObjectContext *)managedObjectContext
{
    if (_managedObjectContext != nil) {
        return _managedObjectContext;
    }
    
    NSPersistentStoreCoordinator *coordinator = [self persistentStoreCoordinator];
    if (coordinator != nil) {
        _managedObjectContext = [[NSManagedObjectContext alloc] init];
        [_managedObjectContext setPersistentStoreCoordinator:coordinator];
    }
    return _managedObjectContext;
}

// Returns the managed object model for the application.
// If the model doesn't already exist, it is created from the application's model.
- (NSManagedObjectModel *)managedObjectModel
{
    if (_managedObjectModel != nil) {
        return _managedObjectModel;
    }
    NSURL *modelURL = [[NSBundle mainBundle] URLForResource:@"SmartHome" withExtension:@"momd"];
    _managedObjectModel = [[NSManagedObjectModel alloc] initWithContentsOfURL:modelURL];
    return _managedObjectModel;
}

// Returns the persistent store coordinator for the application.
// If the coordinator doesn't already exist, it is created and the application's store added to it.
- (NSPersistentStoreCoordinator *)persistentStoreCoordinator
{
    if (_persistentStoreCoordinator != nil) {
        return _persistentStoreCoordinator;
    }
    
    NSURL *storeURL = [[self applicationDocumentsDirectory] URLByAppendingPathComponent:@"SmartHome.sqlite"];
    
    NSError *error = nil;
    _persistentStoreCoordinator = [[NSPersistentStoreCoordinator alloc] initWithManagedObjectModel:[self managedObjectModel]];
    if (![_persistentStoreCoordinator addPersistentStoreWithType:NSSQLiteStoreType configuration:nil URL:storeURL options:nil error:&error]) {
        /*
         Replace this implementation with code to handle the error appropriately.
         
         abort() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development. 
         
         Typical reasons for an error here include:
         * The persistent store is not accessible;
         * The schema for the persistent store is incompatible with current managed object model.
         Check the error message to determine what the actual problem was.
         
         
         If the persistent store is not accessible, there is typically something wrong with the file path. Often, a file URL is pointing into the application's resources directory instead of a writeable directory.
         
         If you encounter schema incompatibility errors during development, you can reduce their frequency by:
         * Simply deleting the existing store:
         [[NSFileManager defaultManager] removeItemAtURL:storeURL error:nil]
         
         * Performing automatic lightweight migration by passing the following dictionary as the options parameter:
         @{NSMigratePersistentStoresAutomaticallyOption:@YES, NSInferMappingModelAutomaticallyOption:@YES}
         
         Lightweight migration will only work for a limited set of schema changes; consult "Core Data Model Versioning and Data Migration Programming Guide" for details.
         
         */
        NSLog(@"Unresolved error %@, %@", error, [error userInfo]);
        abort();
    }    
    
    return _persistentStoreCoordinator;
}

#pragma mark - Application's Documents directory

// Returns the URL to the application's Documents directory.
- (NSURL *)applicationDocumentsDirectory
{
    return [[[NSFileManager defaultManager] URLsForDirectory:NSDocumentDirectory inDomains:NSUserDomainMask] lastObject];
}

#pragma mark - registerPushNotification
//注册PUSH通知
- (void)registerPushNotificationOptions:(NSDictionary *)launchOptions{
    
#if __IPHONE_OS_VERSION_MAX_ALLOWED > __IPHONE_7_1
    if ([[UIDevice currentDevice].systemVersion floatValue] >= 8.0) {
        //categories
        [APService
         registerForRemoteNotificationTypes:(UIUserNotificationTypeBadge |
                                             UIUserNotificationTypeSound |
                                             UIUserNotificationTypeAlert)
         categories:nil];
    } else {
        //categories nil
        [APService
         registerForRemoteNotificationTypes:(UIRemoteNotificationTypeBadge | UIRemoteNotificationTypeSound |UIRemoteNotificationTypeAlert)
#else
         //categories nil
         categories:nil];
        [APService
         registerForRemoteNotificationTypes:(UIRemoteNotificationTypeBadge |
                                             UIRemoteNotificationTypeSound |
                                             UIRemoteNotificationTypeAlert)
#endif
         // Required
         categories:nil];
    }
    [APService setupWithOption:launchOptions];
    
//    UIApplication *application = [UIApplication sharedApplication];
//    if ([application respondsToSelector:@selector(registerForRemoteNotifications)]) {
//        [application registerForRemoteNotifications];
//        UIUserNotificationSettings *settings = [UIUserNotificationSettings settingsForTypes: (UIRemoteNotificationTypeBadge | UIRemoteNotificationTypeAlert | UIRemoteNotificationTypeSound) categories:nil];
//        [[UIApplication sharedApplication] registerUserNotificationSettings:settings];
//    }else {
//        [application registerForRemoteNotificationTypes:(UIRemoteNotificationTypeAlert | UIRemoteNotificationTypeSound | UIRemoteNotificationTypeBadge)];
//    }
}

+(AppDelegate *)sharedDelegate{
    return (AppDelegate *)[[UIApplication sharedApplication] delegate];
}

- (FECoreDataHandler *)coreDataHandler {
    
    if (nil == _coreDataHandler)
        _coreDataHandler = [[FECoreDataHandler alloc] initWithAppDelegateManagedObjectContext];
    return _coreDataHandler;
    
}

@end
