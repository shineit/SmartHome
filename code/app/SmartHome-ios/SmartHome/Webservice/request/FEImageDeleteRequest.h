//
//  FEImageDeleteRequest.h
//  SmartHome
//
//  Created by Seven on 15-4-12.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@interface FEImageDeleteRequest : FERequestBaseData

//private int userID;
//private String imgName;

@property (nonatomic, strong) NSNumber *userID;
@property (nonatomic, strong) NSString *imgName;

-(id)initWithUid:(NSNumber *)uid imageName:(NSString *)iname;

@end
