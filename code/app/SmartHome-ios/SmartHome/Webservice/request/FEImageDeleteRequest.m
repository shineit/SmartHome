//
//  FEImageDeleteRequest.m
//  SmartHome
//
//  Created by Seven on 15-4-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEImageDeleteRequest.h"

@implementation FEImageDeleteRequest

-(id)initWithUid:(NSNumber *)uid imageName:(NSString *)iname{
    self = [super initWithMothed:__METHOD_DELET_IMG];
    if (self) {
        _userID = uid;
        _imgName = iname;
    }
    return self;
}

@end
