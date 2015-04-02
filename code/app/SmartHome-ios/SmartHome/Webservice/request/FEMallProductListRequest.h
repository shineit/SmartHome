//
//  FEMallProductListRequest.h
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"
#import "FEPage.h"

@interface FEMallProductListRequest : FERequestBaseData
@property (nonatomic, strong) NSNumber *userID;
@property (nonatomic, strong) FEPage *page;
@property (nonatomic, strong) NSArray *filterList;

-(id)initWithUid:(NSNumber *)uid page:(FEPage *)page fillter:(NSArray *)filter;
@end
