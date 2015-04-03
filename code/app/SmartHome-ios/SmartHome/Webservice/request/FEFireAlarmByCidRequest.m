//
//  FEFireAlarmByCidRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEFireAlarmByCidRequest.h"

@implementation FEFireAlarmByCidRequest

-(id)initWithUid:(NSNumber *)uid comanyId:(NSNumber *)cid page:(FEPage *)page filterList:(NSArray *)flist{
    self = [super initWithMothed:__METHOD_FIRE_ALARM];
    if (self) {
        _userID = uid;
        _companyID = cid;
        _page = page;
        _filterList = flist;
    }
    return self;
}

@end
