//
//  FECheckButtonGroup.m
//  SmartHome
//
//  Created by Seven on 14-11-2.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECheckButtonGroup.h"
#import "FECheckButton.h"

@interface FECheckButtonGroup ()

@property (nonatomic, strong) NSMutableArray *buttonGroup;

@end

@implementation FECheckButtonGroup

-(id)init{
    self = [super init];
    if (self) {
        _buttonGroup = [NSMutableArray new];
    }
    return self;
}

-(void)addButton:(FECheckButton *)checkbutton{
    [_buttonGroup addObject:checkbutton];
}

-(void)removeButton:(FECheckButton *)checkbutton{
    [_buttonGroup removeObject:checkbutton];
}

-(void)checkButton:(FECheckButton *)checkButon{
    _checkedbtn = checkButon;
    if ([_buttonGroup containsObject:checkButon]) {
        _checkedindex = [_buttonGroup indexOfObject:checkButon];
        for (FECheckButton *btn in _buttonGroup) {
            if (checkButon != btn) {
                [btn setChecked:NO];
            }else{
                [btn setChecked:YES];
            }
        }
    }
}

-(void)uncheckButton:(FECheckButton *)checkButton{
    if (checkButton == _checkedbtn) {
        [checkButton setChecked:NO];
        _checkedbtn = nil;
    }
}

@end
