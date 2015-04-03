//
//  FEGetCompanyRequest.h
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"
#import "FEPage.h"

@interface FEGetCompanyRequest : FERequestBaseData
//private PageJson page;
//private int userID;
@property (nonatomic, strong) NSNumber *userID;
@property (nonatomic, strong) FEPage *page;

-(id)initWithUid:(NSNumber *)uid page:(FEPage *)page;

@end
