//
//  FEPopView.m
//  SmartHome
//
//  Created by Seven on 15-4-13.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEPopView.h"
#import "UIColor+Theme.h"
#import "FEButton.h"
#import <ZBUtilities/UIImage+LogN.h>
#import "UIColor+Hex.h"

@interface FEPopView ()
@property (nonatomic, strong) UIControl *maskview;
@property (nonatomic, weak) UIView *pview;
@end


@implementation FEPopView

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

- (id)initFromView:(UIView *)view action:(void(^)())action
{
    CGFloat height = 300;
    self = [super initWithFrame:CGRectMake(30, (view.bounds.size.height - height) / 2.0, view.bounds.size.width - 60, height)];
    if (self) {
        self.action = action;
        // Initialization code
//        _selectIndex = -1;
        self.backgroundColor = [UIColor ThemeViewBackColor];
        self.center = CGPointMake(view.bounds.size.width / 2, view.bounds.size.height / 2);
        UIControl *mask = [[UIControl alloc] initWithFrame:view.bounds];
        mask.backgroundColor = [UIColor blackColor];
        mask.alpha = 0.5;
        [mask addTarget:self action:@selector(dismiss) forControlEvents:UIControlEventTouchUpInside];
        self.maskview = mask;
        self.pview = view;
        
        [self setUP];
//        _maxHeight = self.pview.bounds.size.height - 150;
        
    }
    return self;
}


-(void)setUP{
    UIView *tview = [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.bounds.size.width, 40)];
    tview.backgroundColor = [UIColor ThemeColor];
    [self addSubview:tview];
    self.tview = tview;
    
    UILabel *titleLabel = [[UILabel alloc] initWithFrame:CGRectMake(2, 0, tview.bounds.size.width - 70, tview.bounds.size.height)];
    titleLabel.textColor = [UIColor whiteColor];
    titleLabel.text = @"   分类";
    [tview addSubview:titleLabel];
    self.tlabel = titleLabel;
    
    FEButton *share = [FEButton buttonWithType:UIButtonTypeCustom];
    share.frame = CGRectMake(_tlabel.frame.origin.x + _tlabel.bounds.size.width + 2, 5, tview.bounds.size.width - (_tlabel.frame.origin.x + _tlabel.bounds.size.width + 2) - 2, tview.bounds.size.height - 10);
    
    [share setBackgroundImage:[UIImage imageFromColor:[UIColor colorWithHex:0x48B805]] forState:UIControlStateNormal];
    share.titleLabel.font = [UIFont systemFontOfSize:12];
    [share addTarget:self action:@selector(share) forControlEvents:UIControlEventTouchUpInside];
    [share setTitle:@"分享到微信" forState:UIControlStateNormal];
    [tview addSubview:share];
    
    _contentLabel = [[UILabel alloc] initWithFrame:CGRectMake(2, 42, self.bounds.size.width - 4, 18)];
    [self addSubview:_contentLabel];
    
    
    UIView *imageBack = [[UIView alloc] initWithFrame:CGRectMake(10, 60, self.bounds.size.width - 20, self.bounds.size.height - 60 - 40)];
    [self addSubview:imageBack];
    imageBack.layer.cornerRadius = 2.0f;
    imageBack.layer.borderColor = [UIColor ThemeColor].CGColor;
    imageBack.layer.borderWidth = 1;
    imageBack.layer.masksToBounds = YES;
    
    _imageView = [[UIImageView alloc] initWithFrame:CGRectMake(3, 3, imageBack.bounds.size.width - 6, imageBack.bounds.size.height - 6)];
    [imageBack addSubview:_imageView];
    
    _personLabel = [[UILabel alloc] initWithFrame:CGRectMake(2, imageBack.frame.origin.y + imageBack.bounds.size.height + 2, self.bounds.size.width - 4, 18)];
    [self addSubview:_personLabel];
    
    _timeLabel = [[UILabel alloc] initWithFrame:CGRectMake(2, _personLabel.frame.origin.y + _personLabel.bounds.size.height + 2, self.bounds.size.width - 4, 18)];
    [self addSubview:_timeLabel];
}

-(void)share{
    [self dismiss];
    if (self.action) {
        self.action();
    }
}

- (void)dismiss{
    
    [UIView animateWithDuration:0.2f
                          delay:0.f
                        options:UIViewAnimationOptionCurveEaseOut
                     animations:^{
                         
                     }
                     completion:^(BOOL finished){
                         [self.maskview removeFromSuperview];
                         [self removeFromSuperview];
                     }];
}

//-(void)dismissNoAnimate{
//    [self.maskview removeFromSuperview];
//    [self removeFromSuperview];
//}

- (void)show{
    // * 44.0f + 40;
    [UIView animateWithDuration:0.2f
                          delay:0.f
                        options:UIViewAnimationOptionCurveEaseOut
                     animations:^{
                         [self.pview addSubview:self.maskview];
                         [self.pview addSubview:self];
//                         self.frame = CGRectMake(30, (self.pview.bounds.size.height - height) / 2.0, self.pview.bounds.size.width - 60, height);
                     }
                     completion:^(BOOL finished){
                         
                     }];
}

-(void)layoutSubviews{
    self.tview.frame = CGRectMake(0, 0, self.bounds.size.width, 40);
    self.tlabel.frame = self.tview.bounds;
    
}

@end
