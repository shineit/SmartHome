//
//  FESensorListRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FESensorListRequest.h"

@implementation FESensorListRequest

-(id)initWithUserID:(NSNumber *)uid page:(FEPage *)page attributes:(NSArray *)attrs{
    self = [super initWithUserID:uid page:page attributes:attrs method:__SENSOR_LIST];
    if (self) {
        
    }
    return self;
}

@end
