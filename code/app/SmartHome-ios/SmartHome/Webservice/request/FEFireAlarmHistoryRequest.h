//
//  FEFireAlarmHistoryRequest.h
//  SmartHome
//
//  Created by Seven on 15-5-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"
#import "FEPage.h"

@interface FEFireAlarmHistoryRequest : FERequestBaseData
@property (nonatomic, strong) NSNumber *userID;
@property (nonatomic, strong) NSNumber *companyID;
@property (nonatomic, strong) FEPage *page;
@property (nonatomic, strong) NSArray *filterList;

-(id)initWithUid:(NSNumber *)uid company:(NSNumber *)cid page:(FEPage *)page filer:(NSArray *)filter;
@end
