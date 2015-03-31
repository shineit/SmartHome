//
//  FEHomePageVC.m
//  SmartHome
//
//  Created by Seven on 15-1-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEHomePageVC.h"
#import "FEHomeItemCell.h"
#import "FEHomeItemCollectionReusableView.h"
#import "AppDelegate.h"
#import "FENewsVC.h"
#import "FEProfileVC.h"
#import "FEServiceListVC.h"
#import "FECurrentWarringVC.h"
#import "FECommonDefine.h"

#define PNG_KEY @"png"
#define ITEM_TITLE   @"title"
#define ITEM_ACTION     @"action"

@interface FEHomePageVC ()<UICollectionViewDataSource,UICollectionViewDelegate,UICollectionViewDelegateFlowLayout>
@property (nonatomic, strong) UICollectionView *collectionView;
@property (nonatomic, strong) NSArray *datasource;
@property (nonatomic, strong) NSArray *headerArray;

@end

@implementation FEHomePageVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"HOME");
    
    
    
    self.datasource =
  @[
    @[@{PNG_KEY:@"home_safe",ITEM_TITLE:kString(@"云安"),ITEM_ACTION:[self getIvocationWith:@selector(tosafe)]},
  @{PNG_KEY:@"home_controll",ITEM_TITLE:kString(@"云控"),ITEM_ACTION:[self getIvocationWith:@selector(tocontroll)]},
  @{PNG_KEY:@"home_camera",ITEM_TITLE:kString(@"云视"),ITEM_ACTION:[self getIvocationWith:@selector(tocamera)]},
  @{PNG_KEY:@"home_warring",ITEM_TITLE:kString(@"告警信息"),ITEM_ACTION:[self getIvocationWith:@selector(towarring)]},
  @{PNG_KEY:@"home_plan",ITEM_TITLE:kString(@"平面图查看"),ITEM_ACTION:[self getIvocationWith:@selector(toscan)]}],
  
  @[@{PNG_KEY:@"home_concentrator",ITEM_TITLE:kString(@"集中器设置"),ITEM_ACTION:[self getIvocationWith:@selector(toconcentrator)]},
  @{PNG_KEY:@"home_region",ITEM_TITLE:kString(@"我的片区"),ITEM_ACTION:[self getIvocationWith:@selector(tomyregion)]}],
  
  @[@{PNG_KEY:@"home_profile",ITEM_TITLE:kString(@"账户设置"),ITEM_ACTION:[self getIvocationWith:@selector(toprofile)]},
  @{PNG_KEY:@"home_service",ITEM_TITLE:kString(@"申请管理"),ITEM_ACTION:[self getIvocationWith:@selector(toservice)]},
  @{PNG_KEY:@"home_news",ITEM_TITLE:kString(@"新闻公告"),ITEM_ACTION:[self getIvocationWith:@selector(tonews)]}]
  ];
    self.headerArray = @[kString(@"与安防"),kString(@"设备管理"),kString(@"个人中心")];
    [self initUI];
}

-(void)tosafe{
    [[AppDelegate sharedDelegate] loadMainSelectAtIndex:1];
}

-(void)tocontroll{
    [[AppDelegate sharedDelegate] loadMainSelectAtIndex:2];
}

-(void)tocamera{
    [[AppDelegate sharedDelegate] loadMainSelectAtIndex:3];
}

-(void)towarring{
    FECurrentWarringVC *cwarring = [FECurrentWarringVC new];
    [self.navigationController pushViewController:cwarring animated:YES];
}

-(void)toscan{
    
}

-(void)toconcentrator{
    
}

-(void)tomyregion{
    
}

-(void)toprofile{
    FEProfileVC *pvc = [FEProfileVC new];
    [self.navigationController pushViewController:pvc animated:YES];
}

-(void)toservice{
    FEServiceListVC *svc = [FEServiceListVC new];
    [self.navigationController pushViewController:svc animated:YES];
}

-(void)tonews{
    FENewsVC *news = [FENewsVC new];
    [self.navigationController pushViewController:news animated:YES];
}

-(NSInvocation *)getIvocationWith:(SEL)selector{
    NSMethodSignature *sig=[self methodSignatureForSelector:selector];
    NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:sig];
    [invocation setSelector:selector];
    [invocation setTarget:self];
    return invocation;
}

-(void)initUI{
    UICollectionViewFlowLayout *layout= [[UICollectionViewFlowLayout alloc]init];
    layout.sectionInset = UIEdgeInsetsMake(20, 0, 20, 0);
    UICollectionView *cview = [[UICollectionView alloc] initWithFrame:self.view.bounds collectionViewLayout:layout];
    cview.alwaysBounceVertical = YES;
    cview.delegate = self;
    cview.dataSource = self;
    cview.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    [cview registerClass:[FEHomeItemCell class] forCellWithReuseIdentifier:@"cell"];
    [cview registerClass:[FEHomeItemCollectionReusableView class] forSupplementaryViewOfKind:UICollectionElementKindSectionHeader withReuseIdentifier:@"header"];
//    [cview registerClass:[FEHomeItemCollectionReusableView class] forCellWithReuseIdentifier:@"header"];
    //    cview.backgroundColor = [UIColor lightGrayColor];
    cview.backgroundColor = [UIColor clearColor];
    self.collectionView = cview;
    [self.view addSubview:cview];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - UICollectionViewDataSource

// The cell that is returned must be retrieved from a call to -dequeueReusableCellWithReuseIdentifier:forIndexPath:
- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    static NSString * identifier = @"cell";
    FEHomeItemCell * cell = [collectionView dequeueReusableCellWithReuseIdentifier:identifier forIndexPath:indexPath];
    
    cell.backgroundColor = [UIColor clearColor];
    cell.itemTitleLabel.text = self.datasource[indexPath.section][indexPath.row][ITEM_TITLE];
    cell.itemImageView.image = [UIImage imageNamed:self.datasource[indexPath.section][indexPath.row][PNG_KEY]];
//    cell.textLabel.text = self.cameras[indexPath.row];
    return cell;
}

-(NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView
{
    return self.datasource.count;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return [self.datasource[section] count];
}

#pragma mark - UICollectionViewDelegateFlowLayout

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath
{
    return CGSizeMake(64, 64);
}

-(UIEdgeInsets)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout insetForSectionAtIndex:(NSInteger)section
{
    return UIEdgeInsetsMake(10, 35, 10, 35);
}

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout referenceSizeForHeaderInSection:(NSInteger)section{
    return CGSizeMake(self.view.bounds.size.width, 40);
}

-(UICollectionReusableView *)collectionView:(UICollectionView *)collectionView viewForSupplementaryElementOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath{
    UICollectionReusableView * reusableview = nil ;
    
    if ( kind == UICollectionElementKindSectionHeader ) {
        FEHomeItemCollectionReusableView * headerView = [collectionView dequeueReusableSupplementaryViewOfKind : UICollectionElementKindSectionHeader withReuseIdentifier :@"header" forIndexPath : indexPath ] ;
        headerView.titleLebal.text = self.headerArray[indexPath.section];
        reusableview = headerView;
    }
    return reusableview;
}

#pragma mark - UICollectionViewDelegate
-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
    NSInvocation *iv = self.datasource[indexPath.section][indexPath.row][ITEM_ACTION];
    [iv invoke];
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
