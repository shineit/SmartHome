//
//  FEControlView.m
//  SmartHome
//
//  Created by Seven on 14-10-22.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEControlView.h"
#import "FECheckButtonGroup.h"
#import "FECheckButton.h"
#import "FECommonDefine.h"

@interface FEControlView ()
@property (nonatomic, strong) FECheckButtonGroup *checkGroup;

@end

@implementation FEControlView

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
        [self setup];
    }
    return self;
}

-(void)setup{
    _checkGroup = [FECheckButtonGroup new];
    
    self.backgroundColor = [UIColor whiteColor];
    
    FECheckButton *open = [[FECheckButton alloc] initWithFrame:CGRectMake(20, 5, self.bounds.size.width / 2.0f - 20, 30)];
    [open setTitle:kString(@"SAFE_ALL_OPEN")];
    [_checkGroup addButton:open];
    [open addObserver:self check:@selector(check:)];
    
    [self addSubview:open];
    
    FECheckButton *close = [[FECheckButton alloc] initWithFrame:CGRectMake(self.bounds.size.width / 2.0f + 20, 5, self.bounds.size.width / 2.0f - 20, 30)];
    [close setTitle:kString(@"SAFE_ALL_CLOSE")];
    [_checkGroup addButton:close];
    [close addObserver:self check:@selector(check:)];
    [self addSubview:close];
    
}

-(void)cancelSelected{
    [_checkGroup uncheckButton:_checkGroup.checkedbtn];
}

-(void)check:(FECheckButton *)btn{
    if (btn == _checkGroup.checkedbtn) {
        return;
    }
    [_checkGroup checkButton:btn];
    if (_checkGroup.checkedindex == 0) {
        if ([self.delegate respondsToSelector:@selector(controlViewDidSelectAllOpen:)]) {
            [self.delegate controlViewDidSelectAllOpen:self];
        }
    }else{
        if ([self.delegate respondsToSelector:@selector(controlViewDidSelectAllClose:)]) {
            [self.delegate controlViewDidSelectAllClose:self];
        }
    }
    
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
}
*/

@end
