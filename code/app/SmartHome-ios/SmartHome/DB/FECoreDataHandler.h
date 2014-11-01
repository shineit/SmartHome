//
//  FECoreDataHandler.h
//  SmartHome
//
//  Created by Seven on 14-10-22.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@class CDUser;

@interface FESortDescriptor : NSObject

@property (retain,nonatomic) NSString *key;
@property (assign,nonatomic) BOOL ascending;

+ (FESortDescriptor *)sortDescriptorWithKey:(NSString *)key ascending:(BOOL)ascending;

@end

@interface FECoreDataHandler : NSObject{

@private
    NSManagedObjectContext *_managedObjectContext;
    
}

@property (retain,nonatomic) NSManagedObjectContext *managedObjectContext;

/*
 *  Create/Save/Delete coredata
 */
- (instancetype)initWithAppDelegateManagedObjectContext;

//fetch user information
-(CDUser *)fetchUser;
-(CDUser *)touchUserByIdentifier:(NSNumber *)identifier;
- (void)saveCoreData;
- (void)deleteCoreData:(NSArray *)array;

@end
