//
//  FEADRequest.h
//  SmartHome
//
//  Created by Seven on 15-4-7.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"
#import "FEPage.h"

@interface FEADRequest : FERequestBaseData

@property (nonatomic, strong) NSNumber *userID;
@property (nonatomic, strong) FEPage *page;
@property (nonatomic, strong) NSArray *filerList;

-(id)initWithUid:(NSNumber *)uid page:(FEPage *)page filter:(NSArray *)filerList;

@end
