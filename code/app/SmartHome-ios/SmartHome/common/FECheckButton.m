//
//  FECheckButton.m
//  SmartHome
//
//  Created by Seven on 14-11-2.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECheckButton.h"

@interface FECheckButton ()

@property (nonatomic, strong) UIImageView *checkImageView;
@property (nonatomic, strong) UILabel *checkTitle;
@property (nonatomic, assign, getter=isChecked) BOOL checked;
@property (nonatomic, weak) id observer;
@property (nonatomic, assign) SEL checkSelector;

@end

@implementation FECheckButton


-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        _checkImageView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, 30, self.bounds.size.height)];
        [self addSubview:_checkImageView];
        
        _checkTitle = [[FELabel alloc] initWithFrame:CGRectMake(_checkImageView.bounds.size.width + 5, 0, self.bounds.size.width - _checkImageView.bounds.size.width - 5, self.bounds.size.height)];
        [self addSubview:_checkTitle];
        
        [self addTarget:self action:@selector(check:) forControlEvents:UIControlEventTouchUpInside];
        [self setChecked:NO];
        
        [self setCheckedImage:[UIImage imageNamed:@"setting_aboutus"]];
        [self setUncheckedImage:[UIImage imageNamed:@"setting_alert"]];
        
    }
    return self;
}

-(void)check:(UIButton *)button{
    
    if (_checked == YES) {
        return;
    }
    if ([self.observer respondsToSelector:self.checkSelector]) {
        [self.observer performSelector:self.checkSelector withObject:self];
    }
    [self setChecked:!_checked];
    
}

-(void)setChecked:(BOOL)checked{
    
    _checked = checked;
    
    if (checked) {
        self.checkImageView.image = self.checkedImage;
    }else{
        self.checkImageView.image = self.uncheckedImage;
    }
}

-(void)setCheckedImage:(UIImage *)checkedImage{
    _checkedImage = checkedImage;
    [self setChecked:_checked];
}

-(void)setUncheckedImage:(UIImage *)uncheckedImage{
    _uncheckedImage = uncheckedImage;
    [self setChecked:_checked];
}

-(void)setTitle:(NSString *)title{
    self.checkTitle.text = title;
}

-(void)addObserver:(id)observer check:(SEL)selector{
    _observer = observer;
    _checkSelector = selector;
}


@end
