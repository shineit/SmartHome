//
//  FEUserMark.h
//  SmartHome
//
//  Created by Seven on 14-11-2.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

//#import "FERequestBaseData.h"
#import "FEDictionaryObject.h"

@interface FEUserMark : FEDictionaryObject

@property (nonatomic, strong, readonly) NSNumber *userID;
@property (nonatomic, strong, readonly) NSString *mark;

-(id)initWithUserID:(NSNumber *)uid mark:(NSString *)mark;

@end
