//
//  FEUser.h
//  SmartHome
//
//  Created by Seven on 14-10-22.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreData/CoreData.h>


@interface FEUser : NSManagedObject

@property (nonatomic, retain) NSString * password;
@property (nonatomic, retain) NSString * userid;
@property (nonatomic, retain) NSString * username;

@end
