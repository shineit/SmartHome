//
//  FEHomeVC.m
//  SmartHome
//
//  Created by Seven on 14-10-18.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEHomeVC.h"
#import "FECollectionViewCell.h"
#import "FEModifyPswVC.h"
#import "FECurrentWarringVC.h"
#import "FEWarringInfoVC.h"
#import "FEProfileVC.h"
#import "FECloudCameraVC.h"
#import "FECloudSafeVC.h"

@interface FEHomeVC ()<UICollectionViewDataSource,UICollectionViewDelegate,UICollectionViewDelegateFlowLayout>

@property (nonatomic, strong) UICollectionView *collectionView;
@property(nonatomic,strong) NSArray *functionItem;

@end

@implementation FEHomeVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"HOME");
        self.functionItem = @[FEString(@"CLOUD_SAFE"),FEString(@"CURRENT_WARRING"),FEString(@"PROFILE"),FEString(@"CLOUD_CAMERA"),FEString(@"MOD_PSW"),FEString(@"WARRING")];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
}

-(void)initUI{
    
    UICollectionViewFlowLayout *layout= [[UICollectionViewFlowLayout alloc]init];
    UICollectionView *cview = [[UICollectionView alloc] initWithFrame:self.view.bounds collectionViewLayout:layout];
    cview.alwaysBounceVertical = YES;
    cview.delegate = self;
    cview.dataSource = self;
    [cview registerClass:[FECollectionViewCell class] forCellWithReuseIdentifier:@"cell"];
    cview.backgroundColor = [UIColor lightGrayColor];
    self.collectionView = cview;
    [self.view addSubview:cview];
    
}

#pragma mark - UICollectionViewDataSource

// The cell that is returned must be retrieved from a call to -dequeueReusableCellWithReuseIdentifier:forIndexPath:
- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    static NSString * identifier = @"cell";
    FECollectionViewCell * cell = [collectionView dequeueReusableCellWithReuseIdentifier:identifier forIndexPath:indexPath];
//    if (!cell) {
//        cell = [[FECollectionViewCell alloc] initWithFrame:CGRectMake(0, 0, 140, 140)];
//    }
    
    cell.backgroundColor = [UIColor whiteColor];
    cell.textlabel.text = self.functionItem[indexPath.row];
    return cell;
}

-(NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView
{
    return 1;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return self.functionItem.count;
}

#pragma mark - UICollectionViewDelegateFlowLayout

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath
{
    return CGSizeMake(140, 140);
}

-(UIEdgeInsets)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout insetForSectionAtIndex:(NSInteger)section
{
    return UIEdgeInsetsMake(10, 10, 10, 10);
}

#pragma mark - UICollectionViewDelegate
-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
//    NSLog(@"tap index %d",indexPath.row);
    FECloudSafeVC *safevc;
    FECurrentWarringVC *cwarring;
    FEProfileVC *profilevc;
    FECloudCameraVC *camera;
    FEModifyPswVC *psw;
    FEWarringInfoVC *warringinfo;
    switch (indexPath.row) {
        case 0:
            safevc = [FECloudSafeVC new];
            [self.navigationController pushViewController:safevc animated:YES];
            break;
        case 1:
            cwarring = [FECurrentWarringVC new];
            [self.navigationController pushViewController:cwarring animated:YES];
            break;
        case 2:
            profilevc = [FEProfileVC new];
            [self.navigationController pushViewController:profilevc animated:YES];
            break;
        case 3:
            camera = [FECloudCameraVC new];
            [self.navigationController pushViewController:camera animated:YES];
            break;
        case 4:
            psw = [FEModifyPswVC new];
            [self.navigationController pushViewController:psw animated:YES];
            break;
        case 5:
            warringinfo = [FEWarringInfoVC new];
            [self.navigationController pushViewController:warringinfo animated:YES];
            break;
            
        default:
            break;
    }
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
