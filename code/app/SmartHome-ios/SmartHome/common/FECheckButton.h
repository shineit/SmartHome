//
//  FECheckButton.h
//  SmartHome
//
//  Created by Seven on 14-11-2.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface FECheckButton : UIButton

@property (nonatomic, strong) UIImage *checkedImage;
@property (nonatomic, strong) UIImage *uncheckedImage;
@property (nonatomic, assign, getter=isChecked) BOOL checked;


-(void)setTitle:(NSString *)title;
-(void)setChecked:(BOOL)checked;
-(void)addObserver:(id)observer check:(SEL)selector;

@end
