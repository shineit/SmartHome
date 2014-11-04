//
//  FEHistoryAlarmRequest.m
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEHistoryAlarmRequest.h"
#import "FEAttribute.h"
#import "FEPage.h"

@implementation FEHistoryAlarmRequest

-(id)initWithUserID:(NSNumber *)uid page:(FEPage *)page attributes:(NSArray *)attrs{
    self = [super initWithUserID:uid page:page attributes:attrs method:__HISTORY_ALARM];
    if (self) {
        
    }
    return self;
}

@end
