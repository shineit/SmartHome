//
//  CDUser.h
//  SmartHome
//
//  Created by Seven on 14-10-31.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreData/CoreData.h>


@interface CDUser : NSManagedObject

@property (nonatomic, retain) NSString * password;
@property (nonatomic, retain) NSNumber * userid;
@property (nonatomic, retain) NSString * username;

@end
