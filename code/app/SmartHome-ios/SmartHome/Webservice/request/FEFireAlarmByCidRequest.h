//
//  FEFireAlarmByCidRequest.h
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"
#import "FEPage.h"

@interface FEFireAlarmByCidRequest : FERequestBaseData
//private int userID;
//private String companyID;
//private PageJson page;
//private List<AttributeJson> filterList;
@property (nonatomic, strong) NSNumber *userID;
@property (nonatomic, strong) NSNumber *companyID;
@property (nonatomic, strong) FEPage *page;
@property (nonatomic, strong) NSArray *filterList;

-(id)initWithUid:(NSNumber *)uid comanyId:(NSNumber *)cid page:(FEPage *)page filterList:(NSArray *)flist;

@end
