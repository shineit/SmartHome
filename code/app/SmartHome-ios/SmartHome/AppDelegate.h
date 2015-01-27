//
//  AppDelegate.h
//  SmartHome
//
//  Created by Seven on 14-10-15.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AppDelegate : UIResponder <UIApplicationDelegate,UITabBarControllerDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (readonly, strong, nonatomic) NSManagedObjectContext *managedObjectContext;
@property (readonly, strong, nonatomic) NSManagedObjectModel *managedObjectModel;
@property (readonly, strong, nonatomic) NSPersistentStoreCoordinator *persistentStoreCoordinator;
@property (readonly, strong, nonatomic) FECoreDataHandler *coreDataHandler;
@property (strong, nonatomic) UITabBarController *tabbarController;
@property (strong, nonatomic) UINavigationController *firstPageController;

- (void)saveContext;
- (NSURL *)applicationDocumentsDirectory;

//load main view
-(void)loadMainSelectAtIndex:(NSInteger)index;
-(void)loadSigin;
-(void)loadFirstPage;

+(AppDelegate *)sharedDelegate;

@end
